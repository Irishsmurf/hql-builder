package org.tools.hqlbuilder.webservice.wicket.components;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.tools.hqlbuilder.webservice.resources.prime.PrimeUI;
import org.tools.hqlbuilder.webservice.wicket.WicketApplication;

import com.googlecode.wicket.jquery.core.settings.IJQueryLibrarySettings;

public class Accordion extends Panel {
    private static final long serialVersionUID = -1117839412897961353L;

    public static final String ACCORDION_ID = "accordion";

    private boolean prime;

    public Accordion(String id, final List<String> titles, final List<Component> components, boolean prime) {
        this(id, titles, components, null, prime);
    }

    public Accordion(String id, final List<String> titles, boolean prime, final List<String> data) {
        this(id, titles, null, data, prime);
    }

    protected Accordion(String id, final List<String> titles, final List<Component> components, final List<String> data, boolean prime) {
        super(id);

        this.prime = prime;

        WebMarkupContainer accordionparent = new WebMarkupContainer(ACCORDION_ID);
        accordionparent.setOutputMarkupId(true);
        add(accordionparent);

        ListView<String> accordion = new ListView<String>("accordion.element", titles) {
            private static final long serialVersionUID = -498565033414119290L;

            @Override
            protected void populateItem(final ListItem<String> item) {
                item.add(new Label("accordion.title", item.getModelObject()));
                WebMarkupContainer container = new WebMarkupContainer("accordion.text") {
                    private static final long serialVersionUID = 79567713291018358L;

                    @Override
                    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
                        if (components != null) {
                            super.onComponentTagBody(markupStream, openTag);
                        } else {
                            String dataString = data.get(titles.indexOf(item.getModelObject()));
                            replaceComponentTagBody(markupStream, openTag, dataString);
                        }
                    }
                };
                container.setOutputMarkupPlaceholderTag(false);
                container.setRenderBodyOnly(true);
                if (components != null) {
                    container.add(components.get(titles.indexOf(item.getModelObject())));
                }
                item.add(container);
                item.setOutputMarkupPlaceholderTag(false);
                item.setRenderBodyOnly(true);
            }
        };
        accordionparent.add(accordion);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        if (prime) {
            response.render(JavaScriptHeaderItem.forReference(PrimeUI.PRIME_UI_JS));
            response.render(CssHeaderItem.forReference(PrimeUI.PRIME_UI_CSS));
            response.render(OnDomReadyHeaderItem.forScript("$('#" + get(ACCORDION_ID).getMarkupId() + "').puiaccordion();"));
        } else {
            response.render(JavaScriptHeaderItem.forReference(((IJQueryLibrarySettings) WicketApplication.get().getJavaScriptLibrarySettings())
                    .getJQueryUIReference()));
            response.render(OnDomReadyHeaderItem.forScript("$('#" + get(ACCORDION_ID).getMarkupId() + "').accordion();"));
        }
    }
}