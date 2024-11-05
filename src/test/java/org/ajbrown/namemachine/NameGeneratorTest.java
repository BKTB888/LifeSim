/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ajbrown.namemachine;

import java.util.List;

import org.helper.ajbrown.namemachine.Gender;
import org.helper.ajbrown.namemachine.Name;
import org.helper.ajbrown.namemachine.NameGenerator;
import org.helper.ajbrown.namemachine.NameGeneratorOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

/**
 * Unit Test for {@link NameGenerator}
 * @author A.J. Brown <a href="mailto:aj@ajbrown.org">aj@ajbrown.org</a>
 */
public class NameGeneratorTest {


    NameGenerator generator;

    @BeforeEach
    public void setUp() {
        generator = new NameGenerator();
    }

    @Test
    public void providedOptionsAreUsed() {
        NameGeneratorOptions options = mock(NameGeneratorOptions.class);
        NameGenerator generator = new NameGenerator( options );

        generator.generateName();

        verify(options).getGenderWeight();
        verify(options, atLeastOnce()).getRandomSeed();
    }

    @Test
    public void randomNamesAreGeneratedWithoutGender() {

        //The generator should be able to generate a single name
        Name oneName = generator.generateName();
        assertNotNull( oneName.gender );
        assertNotNull( oneName.firstName );
        assertNotNull( oneName.lastName );

        //The generator should be able to generate multiple names
        List<Name> names = generator.generateNames(1000);

        assertEquals( 1000, names.size() );

        //Note that there's a rare chance that this will fail even when things are working just fine.  We randomly
        // determine whether a name will be male or female on each pass, so it's plausible (but unlikely) that names
        // end up being the same gender.
        int males = 0;
        int females = 0;
        for( Name name : names ) {
            if( name.gender == Gender.FEMALE ) {
                females++;
            } else {
                males++;
            }
        }

        // We should get both males and females.
        assertTrue(males > 0, "Expected number of male names to be greater than 0");
        assertTrue(females > 0, "Expected number of female names to be greater than 0");

        // Test that the single name generator can generate a name
        assertEquals(Name.class, generator.generateName().getClass());
        assertNotNull( generator.generateName() );
    }

    @Test
    public void randomNamesAreGeneratedWithGender() {

        //The generator should be able to generate a single name
        Name oneMale = generator.generateName( Gender.MALE );
        assertSame( Gender.MALE, oneMale.gender );
        assertNotNull( oneMale.firstName );
        assertNotNull( oneMale.lastName );

        Name oneFemale = generator.generateName( Gender.FEMALE );
        assertSame( Gender.FEMALE, oneFemale.gender );
        assertNotNull( oneFemale.firstName );
        assertNotNull( oneFemale.lastName );

        //The generator should be able to generate multiple names

        List<Name> femaleNames = generator.generateNames(1000, Gender.FEMALE);
        List<Name> maleNames   = generator.generateNames(1900, Gender.MALE);

        assertEquals( 1000, femaleNames.size() );
        assertEquals( 1900, maleNames.size() );

        // All of the female names should be female
        for( Name name : femaleNames ) {
            assertEquals( name.gender, Gender.FEMALE );
        }

        // All of the male names should be male
        for( Name name : maleNames ) {
            assertEquals( name.gender, Gender.MALE );
        }
    }

    @Test
    public void randomNamesAreGeneratedWithoutRandomSeed() {
        NameGenerator generator = new NameGenerator();
        Name oneNameCall1 = generator.generateName();
        List<Name> namesCall1 = generator.generateNames( 1000 );

        generator = new NameGenerator();
        Name oneNameCall2 = generator.generateName();
        List<Name> namesCall2 = generator.generateNames( 1000 );

        assertNotEquals( oneNameCall1, oneNameCall2 );
        assertNotEquals( namesCall1, namesCall2 );
    }

    @Test
    public void randomNamesAreGeneratedWithRandomSeed() {
        NameGeneratorOptions options = new NameGeneratorOptions();
        options.setRandomSeed( 123L );

        NameGenerator generator = new NameGenerator( options );
        Name oneNameSeed123Call1 = generator.generateName();
        List<Name> namesSeed123Call1 = generator.generateNames( 1000 );

        generator = new NameGenerator( options );
        Name oneNameSeed123Call2 = generator.generateName();
        List<Name> namesSeed123Call2 = generator.generateNames( 1000 );

        assertEquals( oneNameSeed123Call1, oneNameSeed123Call2 );
        assertEquals( namesSeed123Call1, namesSeed123Call2 );

        options.setRandomSeed( 456L );
        generator = new NameGenerator( options );
        Name oneNameSeed456 = generator.generateName();
        List<Name> namesSeed456 = generator.generateNames( 1000 );

        assertNotEquals( oneNameSeed123Call1, oneNameSeed456 );
        assertNotEquals( namesSeed123Call1, namesSeed456 );
    }

    @Test
    public void generatedNamesShouldBeCapitalized() {

        List<Name> names = generator.generateNames(1000);
        for( Name name : names ) {
            assertTrue( isCapitalized( name.firstName ) );
            assertTrue( isCapitalized( name.lastName ) );
        }
    }

    private boolean isCapitalized( String string ) {
        String[] parts = new String[]{
            string.substring(0,0), string.substring(1)
        };

        return parts[0].toUpperCase().equals( parts[0] ) && parts[1].toLowerCase().equals( parts[1] );
    }
}
