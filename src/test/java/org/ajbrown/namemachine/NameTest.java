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

import org.model.helper.ajbrown.namemachine.Gender;
import org.model.helper.ajbrown.namemachine.Name;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test for {@link Name}
 *
 * @author A.J. Brown <a href="mailto:aj@ajbrown.org">aj@ajbrown.org</a>
 */
public class NameTest {

    @Test
    public void nameReturnsConstructedValues() {

        Name name1 = new Name( "Buzz", "Aldrin", Gender.MALE );

        assertEquals("Buzz", name1.firstName);
        assertEquals("Aldrin", name1.lastName);
        assertEquals(Gender.MALE, name1.gender);

        Name name2 = new Name( "Sally", "Ride", Gender.FEMALE );

        assertEquals("Sally", name2.firstName);
        assertEquals("Ride", name2.lastName);
        assertEquals(Gender.FEMALE, name2.gender);
    }

    @Test
    public void toStringReturnsFullName() {
        Name name = new Name( "James", "Lovell", Gender.MALE );

        assertEquals("James Lovell", name.toString());
    }

    @Test
    public void testEquals() {
        Name name1 = new Name( "Jack", "Swigert", Gender.MALE );
        Name name2 = new Name( "Jack", "Swigert", Gender.MALE );

        Name name3 = new Name( "Jack", "Swigert", Gender.FEMALE );
        Name name4 = new Name( "Jane", "Swigert", Gender.FEMALE );
        Name name5 = new Name( "Jane", "Shipley", Gender.FEMALE );

        assertEquals(name1, name2);
        assertNotEquals(name1, name3);
        assertNotEquals(name1, name4);
        assertNotEquals(name4, name5);
    }
}
