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

package org.lappsgrid.jupyter.lsd

import groovy.util.logging.Slf4j
import org.lappsgrid.jupyter.groovy.Config
import org.lappsgrid.jupyter.groovy.GroovyKernel
import org.lappsgrid.jupyter.groovy.json.Serializer

/** A Jupyter kernel for the Lappsgrid Services DSL.
 *
 * @author Keith Suderman
 */
@Slf4j('logger')
class LsdKernel extends GroovyKernel {
    public static String GALAXY_HOST = "http://localhost:8000"
    public static String GALAXY_KEY = ""

    public LsdKernel() {
        super(new LsdContext())
    }

    Map info() {
        return [
                protocol_version: '5.0',
                implementation: 'lsd',
                implementation_version: '1.1.0',
                language_info: [
                        name: 'LSD',
                        version: '1.1.0',
                        mimetype: '',
                        file_extension: '.lsd',
                        pygments_lexer: '',
                        codemirror_mode: '',
                        nbconverter_exporter: ''
                ],
                banner: 'Lappsgrid Services DSL',
                help_links: []
        ]
    }

    static void main(String[] args) {
        if (args.length < 1) {
            logger.error "No connection file passed to the LSD kernel."
            System.exit(1)
        }
        File config = new File(args[0])
        if (!config.exists()) {
            logger.error "Kernel configuration not found."
            System.exit(1)
        }

        GALAXY_HOST = System.getenv("GALAXY_HOST")
        if (GALAXY_HOST) {
            logger.info "GALAXY_HOST is $GALAXY_HOST"
        }
        else {
            logger.warn "GALAXY_HOST not set.  You will not be able to communicate with a Galaxy instance."
        }
        GALAXY_KEY = System.getenv("GALAXY_KEY")
        if (GALAXY_KEY) {
            logger.info("GALAXY_KEY is {}", GALAXY_KEY)
        }
        else {
            logger.warn "GALAXY_KEY not set.  You will not be able to communicate with a Galaxy instance."
        }

        GroovyKernel kernel = new LsdKernel()
        kernel.connectionFile = config
        kernel.run()
    }
}
