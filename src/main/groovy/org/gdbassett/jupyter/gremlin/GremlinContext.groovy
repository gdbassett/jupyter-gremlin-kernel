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

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.lappsgrid.jupyter.groovy.context.DefaultGroovyContext

/**
 * @author Keith Suderman
 */
class GremlinContext extends DefaultGroovyContext {

    @Override
    CompilerConfiguration getCompilerConfiguration() {
        ImportCustomizer customizer = new ImportCustomizer()
        [
            'org.apache.tinkerpop.gremlin.process.traversal.dsl.graph'
        // TODO: Replace below imports with appropriate imports from Gremlin
        /*    'org.lappsgrid.api',
            'org.lappsgrid.core',
            'org.lappsgrid.client',
            'org.lappsgrid.discriminator',
            'org.lappsgrid.serialization',
            'org.lappsgrid.serialization.lif',
            'org.lappsgrid.serialization.datasource',
            'org.lappsgrid.metadata' */
        ].each {
            customizer.addStarImports(it)
        }
        // TODO: replace below line if necessary
        //customizer.addStaticImport('org.lappsgrid.discriminator.Discriminators', 'Uri')

        CompilerConfiguration configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(customizer)
        return configuration
    }

}
