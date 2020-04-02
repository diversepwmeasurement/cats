package com.endava.cats.model;

import com.endava.cats.generator.simple.StringGenerator;
import com.mifmif.common.regex.Generex;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.*;
import org.joda.time.DateTime;

import java.util.UUID;

@Builder
@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode(of = "name")
public class CatsHeader {

    private final boolean required;
    private final String name;
    private String value;

    private CatsHeader(Parameter param) {
        this.name = param.getName();
        this.required = param.getRequired() != null && param.getRequired();
        this.value = this.generateValue(param.getSchema());
    }

    public static CatsHeader fromHeaderParameter(Parameter param) {
        return new CatsHeader(param);
    }

    public void withValue(String value) {
        this.value = value;
    }

    public String getTruncatedValue() {
        if (this.value != null && this.value.length() > 50) {
            return this.value.substring(0, 20) + "...[Total length:" + this.value.length() + "]";
        }

        return this.value;
    }


    public CatsHeader copy() {
        return CatsHeader.builder().name(this.name)
                .required(this.required).value(this.value).build();
    }

    private String generateValue(Schema schema) {
        if (schema.getExample() != null) {
            return schema.getExample().toString();
        }

        if ("uuid".equalsIgnoreCase(schema.getFormat())) {
            return UUID.randomUUID().toString();
        }
        if ("date-time".equalsIgnoreCase(schema.getFormat())) {
            return DateTime.now().toString();
        }

        if ("string".equalsIgnoreCase(schema.getType())) {
            String pattern = schema.getPattern() != null ? schema.getPattern() : "[a-zA-Z0-9]*";
            Generex generex = new Generex(StringGenerator.sanitize(pattern));
            int minLength = schema.getMinLength() != null ? schema.getMinLength() : 0;
            int maxLength = schema.getMaxLength() != null ? schema.getMaxLength() : 30;
            if (minLength == 0) {
                minLength = maxLength / 2;
            }

            return generex.random(minLength, maxLength);
        }

        if (schema.getPattern() != null) {
            Generex generex = new Generex(StringGenerator.sanitize(schema.getPattern()));
            return generex.random();
        }


        return this.name;
    }

}
