/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.codec;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;

import static java.lang.String.format;
import static org.bson.assertions.Assertions.notNull;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * Encodes and decodes {@code Character} objects.
 *
 * @since 3.0
 */
public class CharacterCodec implements Codec<Character> {
    @Override
    public void encode(final BsonWriter writer, final Character value, final EncoderContext encoderContext) {
        notNull("value", value);

        writer.writeString(value.toString());
    }

    @Override
    public Character decode(final BsonReader reader, final DecoderContext decoderContext) {
        String string = reader.readString();
        if (string.length() != 1) {
            throw new BsonInvalidOperationException(format("Attempting to decode the string '%s' to a character, but its length is not "
                                                           + "equal to one", string));
        }

        return string.charAt(0);
    }

    @Override
    public Class<Character> getEncoderClass() {
        return Character.class;
    }

}
