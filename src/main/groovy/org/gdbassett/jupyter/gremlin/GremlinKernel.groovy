/*
 * Copyright (c) 2016 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.gdbassett.jupyter.gremlin

import groovy.util.logging.Slf4j
import org.lappsgrid.jupyter.groovy.Config
import org.lappsgrid.jupyter.groovy.GroovyKernel
import org.lappsgrid.jupyter.groovy.json.Serializer

/** A Jupyter kernel for the Lappsgrid Services DSL.
 *
 * @author Keith Suderman
 */
@Slf4j('logger')
class GremlinKernel extends GroovyKernel {
//    public static String GALAXY_HOST = "http://localhost:8000"
//    public static String GALAXY_KEY = ""

    public GremlinKernel() {
        super(new GremlinContext())
    }

    Map info() {
        return [
                protocol_version: '5.0',
                implementation: 'gremlin',
                implementation_version: '0.1.0.9000',
                language_info: [
                        name: 'Gremlin',
                        version: '0.1.0.9000',
                        mimetype: '',
                        file_extension: '.gremlin',
                        pygments_lexer: '',
                        codemirror_mode: '',
                        nbconverter_exporter: ''
                ],
                banner: 'Gremlin Console',
                help_links: []
        ]
    }

    static void main(String[] args) {
        if (args.length < 1) {
            logger.error "No connection file passed to the Gremlin kernel."
            System.exit(1)
        }
        File config = new File(args[0])
        if (!config.exists()) {
            logger.error "Kernel configuration not found."
            System.exit(1)
        }

        GroovyKernel kernel = new GremlinKernel()
        kernel.connectionFile = config
        kernel.run()
    }
}
