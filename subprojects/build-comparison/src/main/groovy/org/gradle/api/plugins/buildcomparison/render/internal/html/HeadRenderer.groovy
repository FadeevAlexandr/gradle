/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.plugins.buildcomparison.render.internal.html

import org.gradle.api.plugins.buildcomparison.compare.internal.BuildComparisonResult

class HeadRenderer implements PartRenderer {

    final String title
    final String encoding

    HeadRenderer(String title, String encoding) {
        this.title = title
        this.encoding = encoding
    }

    void render(BuildComparisonResult result, HtmlRenderContext context) {
        context.render {
            delegate.title(title)
            meta("http-equiv": "Content-Type", content: "text/html; charset=$encoding")
            style {
                mkp.yieldUnescaped loadBaseCss()

                mkp.yieldUnescaped """
                    .${context.diffClass} { color: red; }
                    .${context.equalClass} { color: green; }

                    .container {
                        padding: 30px;
                        background-color: #FFF7F7;
                        border-radius: 10px;
                        border: 1px solid #E0E0E0;
                    }

                    .build-outcome-comparison {
                        padding: 10px 10px 14px 20px;
                        border: 1px solid #D0D0D0;
                        border-radius: 6px;
                        margin-bottom: 1.2em;
                    }

                    .build-outcome-comparison :last-child {
                        margin-bottom: 0;
                    }
                """
            }

        }
    }

    private String loadBaseCss() {
        URL resource = getClass().getResource("base.css")
        if (resource) {
            resource.getText("utf8")
        } else {
            /*
                The file is generated during the build and added to the classpath by the processResources task
                I don't want to make this fatal as it could break developer test runs in the IDE.
                TODO - add a distribution test to make sure the file is there
            */

            " /* base css not found at runtime */ "
        }
    }
}
