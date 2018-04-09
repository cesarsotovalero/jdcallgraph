/*
 * MIT License
 * <p>
 * Copyright (c) 2017 David Krebs
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.dkarv.jdcallgraph.out.target.mapper;

import com.dkarv.jdcallgraph.out.target.Mapper;
import com.dkarv.jdcallgraph.out.target.Processor;
import java.io.IOException;

/**
 * A mapper that collects multiple graphs.
 */
public abstract class CollectorMapper extends Mapper {
  private final String id;
  private boolean started = false;

  public CollectorMapper(Processor next, boolean addId, String id) {
    super(next, addId);
    this.id = id;
  }

  @Override
  public void start(String[] ids) throws IOException {
    if (!started) {
      next.start(super.extend(ids, id));
      started = true;
    }
  }

  @Override
  public boolean isCollecting() {
    return true;
  }
}
