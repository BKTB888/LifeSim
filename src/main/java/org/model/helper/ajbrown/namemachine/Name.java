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
package org.model.helper.ajbrown.namemachine;

import java.io.Serializable;

/**
 * An instance of a generated name.  Name's are immutable.
 *
 * @author A.J. Brown <a href="mailto:aj@ajbrown.org">aj@ajbrown.org</a>
 */
public class Name implements Serializable {

  public String firstName;
  public String lastName;
  public Gender gender;

  public Name(String firstName, String lastName, Gender gender){
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }

  @Override
  public String toString() {
    return this.firstName + ' ' + this.lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Name name = (Name) o;
    return  firstName.equals(name.firstName) &&
            lastName.equals(name.lastName) &&
            gender == name.gender;
  }
}
