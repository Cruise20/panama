/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package java.foreign;

import java.util.Optional;
import java.util.OptionalLong;

/**
 * A padding layout. A padding layout specifies the size of extra space which is typically not accessed by applications,
 * and is only used for aligning other layout elements around word boundaries.
 * <p>
 * This is a <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>
 * class; use of identity-sensitive operations (including reference equality
 * ({@code ==}), identity hash code, or synchronization) on instances of
 * {@code PaddingLayout} may have unpredictable results and should be avoided.
 * The {@code equals} method should be used for comparisons.
 *
 * @implSpec
 * This class is immutable and thread-safe.
 */
public class PaddingLayout extends AbstractLayout implements Layout {
    private final long size;

    PaddingLayout(long size, OptionalLong alignment, Optional<String> name) {
        super(alignment, name);
        this.size = size;
    }

    /**
     * Create a new padding layout with given size.
     * @param size the padding size in bits.
     * @return the new selector layout.
     * @throws IllegalArgumentException if size is &le; 0.
     */
    public static PaddingLayout of(long size) {
        checkSize(size);
        return new PaddingLayout(size, OptionalLong.empty(), Optional.empty());
    }

    @Override
    public long bitsSize() {
        return size;
    }

    @Override
    long naturalAlignmentBits() {
        return size;
    }

    @Override
    public String toString() {
        return decorateLayoutString("x" + size);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        if (!(other instanceof PaddingLayout)) {
            return false;
        }
        PaddingLayout p = (PaddingLayout)other;
        return size == p.size;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Long.hashCode(size);
    }

    @Override
    PaddingLayout dup(OptionalLong alignment, Optional<String> name) {
        return new PaddingLayout(size, alignment, name);
    }

    //hack: the declarations below are to make javadoc happy; we could have used generics in AbstractLayout
    //but that causes issues with javadoc, see JDK-8224052

    /**
     * {@inheritDoc}
     */
    @Override
    public PaddingLayout withName(String name) {
        return (PaddingLayout)super.withName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaddingLayout alignTo(long alignmentBits) throws IllegalArgumentException {
        return (PaddingLayout)super.alignTo(alignmentBits);
    }
}
