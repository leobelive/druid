/*
 * Druid - a distributed column store.
 * Copyright 2012 - 2015 Metamarkets Group Inc.
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
 */

package io.druid.query.aggregation;

import io.druid.segment.FloatColumnSelector;

import java.nio.ByteBuffer;

/**
 */
public class DoubleMinBufferAggregator implements BufferAggregator
{
  private final FloatColumnSelector selector;

  public DoubleMinBufferAggregator(FloatColumnSelector selector)
  {
    this.selector = selector;
  }

  @Override
  public void init(ByteBuffer buf, int position)
  {
    buf.putDouble(position, Double.POSITIVE_INFINITY);
  }

  @Override
  public void aggregate(ByteBuffer buf, int position)
  {
    buf.putDouble(position, Math.min(buf.getDouble(position), (double) selector.get()));
  }

  @Override
  public Object get(ByteBuffer buf, int position)
  {
    return buf.getDouble(position);
  }

  @Override
  public float getFloat(ByteBuffer buf, int position)
  {
    return (float) buf.getDouble(position);
  }


  @Override
  public long getLong(ByteBuffer buf, int position)
  {
    return  (long) buf.getDouble(position);
  }

  @Override
  public void close()
  {
    // no resources to cleanup
  }
}
