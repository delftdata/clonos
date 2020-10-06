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
package org.apache.flink.runtime.io.network.api;

import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.core.memory.DataOutputView;
import org.apache.flink.runtime.causal.VertexID;
import org.apache.flink.runtime.event.AbstractEvent;

import java.io.IOException;

public class DeterminantRequestEvent extends AbstractEvent {

	private VertexID failedVertex;
	private long startEpochID;

	public DeterminantRequestEvent(VertexID failedVertex, long startEpochID) {
		this.failedVertex = failedVertex;
		this.startEpochID = startEpochID;
	}

	public DeterminantRequestEvent() {
	}

	public VertexID getFailedVertex() {
		return failedVertex;
	}

	public void setFailedVertex(VertexID failedVertex) {
		this.failedVertex = failedVertex;
	}

	public long getStartEpochID() {
		return startEpochID;
	}

	public void setStartEpochID(long startEpochID) {
		this.startEpochID = startEpochID;
	}

	@Override
	public void write(DataOutputView out) throws IOException {
		out.writeShort(this.failedVertex.getVertexID());
		out.writeLong(startEpochID);
	}

	@Override
	public void read(DataInputView in) throws IOException {
		this.setFailedVertex(new VertexID(in.readShort()));
		this.setStartEpochID(in.readLong());
	}

	@Override
	public String toString() {
		return "DeterminantRequestEvent{" +
			"failedVertex=" + failedVertex +
			", startEpochID= " + startEpochID +
			'}';
	}
}
