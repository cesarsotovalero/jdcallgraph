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
package com.dkarv.jdcallgraph.out.target;

import com.dkarv.jdcallgraph.util.OsUtils;
import com.dkarv.jdcallgraph.util.node.Node;
import com.dkarv.jdcallgraph.out.target.writer.CsvFileWriter;
import com.dkarv.jdcallgraph.out.target.writer.DotFileWriter;

import java.io.IOException;

public abstract class Writer implements Processor {
  public static Writer getFor(String w) {
    switch (w.trim()) {
      case "dot":
        return new DotFileWriter();
      case "csv":
        return new CsvFileWriter();
    }
    throw new IllegalArgumentException("Invalid writer: " + w);
  }

  @Override
  public abstract void node(Node method) throws IOException;

  public String buildFilename(String[] ids, String extension) {
    StringBuilder filename = new StringBuilder();
    boolean first = true;
    for (String id : ids) {
      if (!first) {
        filename.append(OsUtils.fileSeparator());
      } else {
        first = false;
      }
      filename.append(id);
    }
    filename.append('.');
    filename.append(extension);
    return filename.toString();
  }

  @Override
  public boolean isCollecting() {
    return false;
  }
}