package net.enginnerringdigest.journalapp.Service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import net.enginnerringdigest.journalapp.entity.User;

import java.util.stream.Stream;

// Assuming you have a User class with a builder pattern
public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
            Arguments.of(User.builder().userName("shyam").password("shyam").build())
        );
    }
}
