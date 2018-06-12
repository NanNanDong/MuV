/*
 * Copyright (C) 2017 BullyBoo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.newx.muv.util.encrypt.hashes;

/**
 * The Adler32 Message-Digest Algorithm
 */

class Adler32 {

    public String getHash(String input){

        byte[] bytes = input.getBytes();

        short s1 = 1;
        short s2 = 0;

        int size = bytes.length;

        for (int i = 0; i < size; i++){
            s1 += bytes[i];
            s2 += s1;

        }

        s1 %= 65521;
        s2 %= 65521;

        return getOutput(s2 * 65536 + s1);
    }

    private String getOutput(int i){

        return Integer.toHexString(i);
    }

}
