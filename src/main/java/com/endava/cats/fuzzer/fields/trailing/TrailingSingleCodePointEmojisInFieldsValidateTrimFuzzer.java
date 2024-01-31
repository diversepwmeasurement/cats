package com.endava.cats.fuzzer.fields.trailing;

import com.endava.cats.annotations.EmojiFuzzer;
import com.endava.cats.annotations.FieldFuzzer;
import com.endava.cats.annotations.ValidateAndTrim;
import com.endava.cats.args.FilesArguments;
import com.endava.cats.http.ResponseCodeFamily;
import com.endava.cats.io.ServiceCaller;
import com.endava.cats.report.TestCaseListener;
import com.endava.cats.util.CatsUtil;
import jakarta.inject.Singleton;

/**
 * Fuzzer that trails valid field values with single code point emojis.
 */
@Singleton
@FieldFuzzer
@EmojiFuzzer
@ValidateAndTrim
public class TrailingSingleCodePointEmojisInFieldsValidateTrimFuzzer extends TrailingSingleCodePointEmojisInFieldsTrimValidateFuzzer {

    /**
     * Creates a new TrailingSingleCodePointEmojisInFieldsValidateTrimFuzzer instance.
     *
     * @param sc the service caller
     * @param lr the test case listener
     * @param cu utility class
     * @param cp files arguments
     */
    protected TrailingSingleCodePointEmojisInFieldsValidateTrimFuzzer(ServiceCaller sc, TestCaseListener lr, CatsUtil cu, FilesArguments cp) {
        super(sc, lr, cu, cp);
    }

    @Override
    public ResponseCodeFamily getExpectedHttpCodeWhenOptionalFieldsAreFuzzed() {
        return ResponseCodeFamily.FOURXX;
    }

    @Override
    public ResponseCodeFamily getExpectedHttpCodeWhenFuzzedValueNotMatchesPattern() {
        return ResponseCodeFamily.FOURXX;
    }

    @Override
    public ResponseCodeFamily getExpectedHttpCodeWhenRequiredFieldsAreFuzzed() {
        return ResponseCodeFamily.FOURXX;
    }
}