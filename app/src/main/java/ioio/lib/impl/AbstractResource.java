/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.IllegalStateException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package ioio.lib.impl;

import ioio.lib.api.Closeable;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.impl.IOIOImpl;
import ioio.lib.impl.IncomingState;

class AbstractResource
implements Closeable,
IncomingState.DisconnectListener {
    protected final IOIOImpl ioio_;
    protected State state_ = State.OPEN;

    public AbstractResource(IOIOImpl iOIOImpl) throws ConnectionLostException {
        this.ioio_ = iOIOImpl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void checkState() throws ConnectionLostException {
        AbstractResource abstractResource = this;
        synchronized (abstractResource) {
            if (this.state_ == State.CLOSED) {
                throw new IllegalStateException("Trying to use a closed resouce");
            }
            if (this.state_ == State.DISCONNECTED) {
                throw new ConnectionLostException();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() {
        AbstractResource abstractResource = this;
        synchronized (abstractResource) {
            if (this.state_ == State.CLOSED) {
                throw new IllegalStateException("Trying to use a closed resouce");
            }
            if (this.state_ == State.DISCONNECTED) {
                this.state_ = State.CLOSED;
            } else {
                this.state_ = State.CLOSED;
                this.ioio_.removeDisconnectListener(this);
            }
            return;
        }
    }

    @Override
    public void disconnected() {
        AbstractResource abstractResource = this;
        synchronized (abstractResource) {
            if (this.state_ != State.CLOSED) {
                this.state_ = State.DISCONNECTED;
            }
            return;
        }
    }

    static final class State
    extends Enum<State> {
        public static final /* enum */ State CLOSED;
        public static final /* enum */ State DISCONNECTED;
        private static final /* synthetic */ State[] ENUM$VALUES;
        public static final /* enum */ State OPEN;

        static {
            OPEN = new State("OPEN", 0);
            CLOSED = new State("CLOSED", 1);
            DISCONNECTED = new State("DISCONNECTED", 2);
            State[] arrstate = new State[]{OPEN, CLOSED, DISCONNECTED};
            ENUM$VALUES = arrstate;
        }

        private State(String string3, int n2) {
            super(string2, n);
        }

        public static State valueOf(String string2) {
            return (State)Enum.valueOf((Class)State.class, (String)string2);
        }

        public static State[] values() {
            State[] arrstate = ENUM$VALUES;
            int n = arrstate.length;
            State[] arrstate2 = new State[n];
            System.arraycopy((Object)arrstate, (int)0, (Object)arrstate2, (int)0, (int)n);
            return arrstate2;
        }
    }

}

