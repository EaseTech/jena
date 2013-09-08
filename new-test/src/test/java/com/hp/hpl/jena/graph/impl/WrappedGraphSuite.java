/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.hpl.jena.graph.impl;

import org.junit.runners.Suite;
/**
 Tests that check GraphMem and WrappedGraph for correctness against the Graph
 and reifier test suites.
 */

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.hp.hpl.jena.graph.AbstractGraphSuite;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.impl.WrappedGraph;
import com.hp.hpl.jena.sparql.graph.GraphFactory;
import com.hp.hpl.jena.testing_framework.AbstractGraphProducer;

@RunWith(Suite.class)
public class WrappedGraphSuite extends AbstractGraphSuite {
	@BeforeClass
	public static void beforeClass() {
		setGraphProducer(new GraphProducer());
	}

	public static class GraphProducer extends AbstractGraphProducer {
		@Override
		public Graph createNewGraph() {
			return new WrappedGraph(GraphFactory.createGraphMem());
		}
	}

}