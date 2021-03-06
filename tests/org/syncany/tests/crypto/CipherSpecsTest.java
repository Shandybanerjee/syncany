/*
 * Syncany, www.syncany.org
 * Copyright (C) 2011-2013 Philipp C. Heckel <philipp.heckel@gmail.com> 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.syncany.tests.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Map;

import org.junit.Test;
import org.syncany.crypto.CipherSpec;
import org.syncany.crypto.CipherSpecs;

public class CipherSpecsTest {
	@Test
	public void testCipherSpecs() {
		Map<Integer, CipherSpec> availableCipherSpecs = CipherSpecs.getAvailableCipherSpecs();
		
		assertEquals(4, availableCipherSpecs.size());
		assertEquals(availableCipherSpecs.get(1).getAlgorithm(), "AES/GCM/NoPadding");		
	}
	
	@Test
	public void testCipherSpec2() {
		CipherSpec twofish128CipherSpec = CipherSpecs.getCipherSpec(2);
		
		assertEquals(twofish128CipherSpec.getId(), 2);
		assertEquals(twofish128CipherSpec.getAlgorithm(), "Twofish/GCM/NoPadding");
		assertEquals(twofish128CipherSpec.getKeySize(), 128);
		assertEquals(twofish128CipherSpec.getIvSize(), 128);
	}
	
	@Test(expected=Exception.class)
	public void testNewEcbCipherSpec() {
		new CipherSpec(0xFF, "AES/ECB/PKCS5Padding", 128, 128, false);
	}
	
	@Test
	public void testCipherSpecHashCodeEquals() {
		CipherSpec cipherSpec1 = CipherSpecs.getCipherSpec(1);
		CipherSpec cipherSpec2 = CipherSpecs.getCipherSpec(2);
		
		assertNotSame(cipherSpec1.hashCode(), cipherSpec2.hashCode());
		assertNotSame(cipherSpec1, cipherSpec2);
		assertEquals(cipherSpec1, new CipherSpec(0x01, "AES/GCM/NoPadding", 128, 128, false));
	}
}
