/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
package jdk.internal.foreign.abi;

public enum StorageClass {

    //Note: The order of the argument/return register classes is subtly relevant, as that is the order in which the
    //binding words will be laid out in the input/output long arrays by the universal invoker.
    STACK_ARGUMENT_SLOT(true, false),
    VECTOR_ARGUMENT_REGISTER(true, false),
    INTEGER_ARGUMENT_REGISTER(true, false),
    VECTOR_RETURN_REGISTER(false, true),
    INTEGER_RETURN_REGISTER(false, true);

    final boolean isArgumentClass;
    final boolean isReturnClass;

    StorageClass(boolean isArgumentClass, boolean isReturnClass) {
        this.isArgumentClass = isArgumentClass;
        this.isReturnClass = isReturnClass;
    }

    public boolean isArgumentClass() {
        return isArgumentClass;
    }

    public boolean isReturnClass() {
        return isReturnClass;
    }
}
