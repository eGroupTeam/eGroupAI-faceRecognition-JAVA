package com.egroup.util;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

public class UUIDGenerator {

  public String getBase64UUID() {
    final Base64 base64 = new Base64();
    final UUID uuid = UUID.randomUUID();
    final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return base64.encodeBase64URLSafeString(bb.array()).replaceAll("_", "").replaceAll("-", "");
  }

  public String getUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /**
   * 12位數衝突機率:1-e^(-(10^9)^2/62^12)=0.00030990773 16位數衝突機率: 1-e^(-(10^10)^2/62^16)=2.09764972e-9 *
   * 
   * @author daniel
   *
   * @param size
   * @return
   */
  public String getRadomUUID(int size) {
    final Random random = new Random();
    final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    final char[] cs = new char[size];

    for (int i = 0; i < cs.length; i++) {
      cs[i] = digits[random.nextInt(digits.length)];
    }
    return new String(cs);
  }

  // public static void main(String args[]) {
  // Set<String> uuidSet = new HashSet<String>();
  // String uuid;
  // int conflictCount = 0;
  // for (int i = 0; i < 20000000; i++) {
  // uuid = getRadomID(12);
  // if (uuidSet.contains(uuid)) {
  // conflictCount++;
  // }
  // uuidSet.add(uuid);
  // System.out.println("i=" + i + ",uuid=" + uuid + ",conflictCount=" + conflictCount);
  // }
  // }
}

