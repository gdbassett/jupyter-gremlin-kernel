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

import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.apache.tinkerpop.gremlin.driver.Client
import org.apache.tinkerpop.gremlin.driver.Cluster
import org.apache.tinkerpop.gremlin.driver.Host
import org.apache.tinkerpop.gremlin.driver.LoadBalancingStrategy
import org.apache.tinkerpop.gremlin.driver.MessageSerializer
import org.apache.tinkerpop.gremlin.driver.Result
import org.apache.tinkerpop.gremlin.driver.ResultSet
import org.apache.tinkerpop.gremlin.driver.Tokens
import org.apache.tinkerpop.gremlin.driver.exception.ConnectionException
import org.apache.tinkerpop.gremlin.driver.exception.ResponseException
import org.apache.tinkerpop.gremlin.driver.message.RequestMessage
import org.apache.tinkerpop.gremlin.driver.message.ResponseMessage
import org.apache.tinkerpop.gremlin.driver.message.ResponseResult
import org.apache.tinkerpop.gremlin.driver.message.ResponseStatus
import org.apache.tinkerpop.gremlin.driver.message.ResponseStatusCode
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteTraversal
import org.apache.tinkerpop.gremlin.driver.ser.GraphBinaryMessageSerializerV1
import org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerGremlinV1d0
import org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerGremlinV2d0
import org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0
import org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV2d0
import org.apache.tinkerpop.gremlin.driver.ser.GryoLiteMessageSerializerV1d0
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV1d0
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV3d0
import org.apache.tinkerpop.gremlin.driver.ser.JsonBuilderGryoSerializer
import org.apache.tinkerpop.gremlin.driver.ser.MessageTextSerializer
import org.apache.tinkerpop.gremlin.driver.ser.SerTokens
import org.apache.tinkerpop.gremlin.driver.ser.SerializationException
import org.apache.tinkerpop.gremlin.driver.ser.Serializers
import org.apache.tinkerpop.gremlin.jsr223.CoreImports
import org.apache.tinkerpop.gremlin.jsr223.DefaultImportCustomizer
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.lappsgrid.jupyter.groovy.context.DefaultGroovyContext

/**
 * @author Keith Suderman
 */
class GremlinContext extends DefaultGroovyContext {

    private static org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer coreImports = DefaultImportCustomizer.build()
            .addClassImports(CoreImports.getClassImports())
            .addFieldImports(CoreImports.getFieldImports())
            .addEnumImports(CoreImports.getEnumImports())
            .addMethodImports(CoreImports.getMethodImports()).create()

    private static org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer driverImports = DefaultImportCustomizer.build()
            .addClassImports(TinkerGraph.class,
                    Cluster.class,
                    Client.class,
                    Host.class,
                    LoadBalancingStrategy.class,
                    MessageSerializer.class,
                    Result.class,
                    ResultSet.class,
                    Tokens.class,
                    ConnectionException.class,
                    ResponseException.class,
                    RequestMessage.class,
                    ResponseMessage.class,
                    ResponseResult.class,
                    ResponseStatus.class,
                    ResponseStatusCode.class,
                    GraphSONMessageSerializerGremlinV1d0.class,
                    GraphSONMessageSerializerGremlinV2d0.class,
                    GraphSONMessageSerializerV1d0.class,
                    GraphSONMessageSerializerV2d0.class,
                    GryoLiteMessageSerializerV1d0.class,
                    GryoMessageSerializerV1d0.class,
                    GryoMessageSerializerV3d0.class,
                    GraphBinaryMessageSerializerV1.class,
                    JsonBuilderGryoSerializer.class,
                    MessageTextSerializer.class,
                    SerializationException.class,
                    Serializers.class,
                    SerTokens.class,
                    DriverRemoteConnection.class,
                    DriverRemoteTraversal.class).create()

    @Override
    CompilerConfiguration getCompilerConfiguration() {
        def customizers = [coreImports, driverImports]

        def imports = new ImportCustomizer()

        // use same model as ImportGroovyCustomizer
        imports.addStaticStars(org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.class.getCanonicalName())
        for (org.apache.tinkerpop.gremlin.jsr223.ImportCustomizer customizer : customizers) {
            customizer.getClassImports().each{ imports.addImports(it.getCanonicalName()) }
            customizer.getMethodImports().
                    find{ it.getDeclaringClass() != org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.class }.
                    each{ imports.addStaticImport(it.getDeclaringClass().getCanonicalName(), it.getName()) }
            customizer.getEnumImports().each{ imports.addStaticImport(it.getDeclaringClass().getCanonicalName(), it.name()) }
            customizer.getFieldImports().each{ imports.addStaticImport(it.getDeclaringClass().getCanonicalName(), it.getName()) }
        }

        def configuration = new CompilerConfiguration()
        configuration.addCompilationCustomizers(imports)
        return configuration
    }

}