package com.endava.cats.fuzzer.headers.base;

import com.endava.cats.fuzzer.http.ResponseCodeFamily;
import com.endava.cats.io.ServiceCaller;
import com.endava.cats.report.TestCaseListener;

public abstract class ExpectOnly4XXBaseHeadersFuzzer extends BaseHeadersFuzzer {
    protected ExpectOnly4XXBaseHeadersFuzzer(ServiceCaller sc, TestCaseListener lr) {
        super(sc, lr);
    }

    @Override
    public ResponseCodeFamily getExpectedHttpCodeForRequiredHeadersFuzzed() {
        return ResponseCodeFamily.FOURXX;
    }

    @Override
    public ResponseCodeFamily getExpectedHttpForOptionalHeadersFuzzed() {
        return ResponseCodeFamily.FOURXX;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}