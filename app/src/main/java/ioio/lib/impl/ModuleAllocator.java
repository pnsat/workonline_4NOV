/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.IllegalArgumentException
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collection
 *  java.util.HashSet
 *  java.util.Iterator
 *  java.util.Set
 *  java.util.TreeSet
 */
package ioio.lib.impl;

import ioio.lib.api.exception.OutOfResourceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class ModuleAllocator {
    private final Set<Integer> allocatedModuleIds_;
    private final Set<Integer> availableModuleIds_;
    private final String name_;

    public ModuleAllocator(int n, String string2) {
        super(ModuleAllocator.getList(n), string2);
    }

    public ModuleAllocator(Collection<Integer> collection, String string2) {
        this.availableModuleIds_ = new TreeSet(collection);
        this.allocatedModuleIds_ = new HashSet();
        this.name_ = string2;
    }

    public ModuleAllocator(int[] arrn, String string2) {
        super(ModuleAllocator.getList(arrn), string2);
    }

    private static Collection<Integer> getList(int n) {
        ArrayList arrayList = new ArrayList();
        int n2 = 0;
        while (n2 < n) {
            arrayList.add((Object)n2);
            ++n2;
        }
        return arrayList;
    }

    private static Collection<Integer> getList(int[] arrn) {
        ArrayList arrayList = new ArrayList(arrn.length);
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            arrayList.add((Object)arrn[n2]);
            ++n2;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Integer allocateModule() {
        ModuleAllocator moduleAllocator = this;
        synchronized (moduleAllocator) {
            if (this.availableModuleIds_.isEmpty()) {
                throw new OutOfResourceException("No more resources of the requested type: " + this.name_);
            }
            Integer n = (Integer)this.availableModuleIds_.iterator().next();
            this.availableModuleIds_.remove((Object)n);
            this.allocatedModuleIds_.add((Object)n);
            return n;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void releaseModule(int n) {
        void var5_2 = this;
        synchronized (var5_2) {
            if (!this.allocatedModuleIds_.contains((Object)n)) {
                throw new IllegalArgumentException("moduleId: " + n + "; not yet allocated");
            }
            this.availableModuleIds_.add((Object)n);
            this.allocatedModuleIds_.remove((Object)n);
            return;
        }
    }
}

