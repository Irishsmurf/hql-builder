package org.tools.hqlbuilder.webservice.wicket.pages;

import static org.tools.hqlbuilder.webservice.wicket.WebHelper.create;
import static org.tools.hqlbuilder.webservice.wicket.WebHelper.name;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDateTime;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tools.hqlbuilder.test.Registration;
import org.tools.hqlbuilder.webclient.HqlWebServiceClient;
import org.tools.hqlbuilder.webservice.wicket.DefaultWebPage;
import org.tools.hqlbuilder.webservice.wicket.MountedPage;
import org.tools.hqlbuilder.webservice.wicket.forms.FormPanel;

@MountedPage("/form/registration")
public class RegistrationPage extends DefaultWebPage {
    private static final long serialVersionUID = 264876407045636533L;

    @SpringBean
    protected transient HqlWebServiceClient hqlWebClient;

    @SpringBean
    protected transient PasswordEncoder passwordEncoder;

    public RegistrationPage(PageParameters parameters) {
        super(parameters);

        FormPanel<Registration> formPanel = new FormPanel<Registration>("userdata.form", Registration.class, true, false) {
            private static final long serialVersionUID = -2653547660762438431L;

            @Override
            protected void submit(IModel<Registration> model) {
                Registration object = model.getObject();
                object.setPassword(passwordEncoder.encode(object.getPassword()));
                object.setVerification(new LocalDateTime());
                Serializable id = hqlWebClient.save(object);
                object = hqlWebClient.get(object.getClass(), id);
                model.setObject(object);
            }
        };
        add(formPanel);

        Registration proxy = create(Registration.class);

        formPanel.addTextField(name(proxy.getUsername()), true);
        formPanel.addTextField(name(proxy.getFirstName()), true);
        formPanel.addTextField(name(proxy.getLastName()), true);
        formPanel.addEmailTextField(name(proxy.getEmail()), true);
        formPanel.addDatePicker(name(proxy.getDateOfBirth()), false);
        formPanel.addPasswordTextField(name(proxy.getPassword()), true);
    }
}
