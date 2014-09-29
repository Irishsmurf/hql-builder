package org.tools.hqlbuilder.webservice.wicket;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class CheckAdsEnabled extends Panel {
    private static final long serialVersionUID = -5845042169704523961L;

    public static JavaScriptResourceReference JS = new JavaScriptResourceReference(CheckAdsEnabled.class, "CheckAdsEnabled.js");

    public static final String IMG_NAME = "ads.gif";

    public static PackageResourceReference IMG = new PackageResourceReference(CheckJavaScriptEnabled.class, IMG_NAME) {
        private static final long serialVersionUID = 4214735061850976515L;

        @Override
        public String getName() {
            return IMG_NAME;
        }

        @Override
        protected String getMinifiedName() {
            return getName();
        };

        @Override
        public String getStyle() {
            return null;
        };

        @Override
        public String getVariation() {
            return null;
        };
    };

    static {
        WebApplication.get().mountResource(IMG_NAME, IMG);
    }

    public CheckAdsEnabled() {
        super("check.ads.enabled");
        add(new Image("check.ads.img") {
            private static final long serialVersionUID = 9061259704592785374L;

            @Override
            protected ResourceReference getImageResourceReference() {
                return IMG;
            }
        });
        setVisible(WicketApplication.get().isCheckAdsEnabled());
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        if (!isEnabledInHierarchy()) {
            return;
        }
        response.render(JavaScriptHeaderItem.forReference(JS));
    }
}
