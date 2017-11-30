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
package com.dkarv.jdcallgraph.util.target;

import com.dkarv.jdcallgraph.util.target.mapper.*;

public abstract class Mapper implements Processor {
  protected final Processor next;

  public Mapper(Processor next) {
    this.next = next;
  }

  @Override
  public boolean needs(Property p) {
    return next.needs(p);
  }

  public static Mapper getFor(String specification, Processor next) {
    switch (specification.trim()) {
      case "thread":
        return new ThreadsMapper(next);
      case "entry":
        // FIXME
        return new ThreadsMapper(next);
      case "test":
        return new ThreadsMapper(next);
    }
    throw new IllegalArgumentException("Unknown mapper: " + specification);
  }
}
