package com.Project.QuizadeuxApplication.Entities;

public class InteractiveMessage {

        private String type;
        private Header header;
        private Body body;
        private Footer footer;
        private Action action;

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public Header getHeader() {
                return header;
        }

        public void setHeader(Header header) {
                this.header = header;
        }

        public Body getBody() {
                return body;
        }

        public void setBody(Body body) {
                this.body = body;
        }

        public Footer getFooter() {
                return footer;
        }

        public void setFooter(Footer footer) {
                this.footer = footer;
        }

        public Action getAction() {
                return action;
        }

        public void setAction(Action action) {
                this.action = action;
        }
}
