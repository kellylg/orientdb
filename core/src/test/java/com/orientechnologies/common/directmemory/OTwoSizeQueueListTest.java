package com.orientechnologies.common.directmemory;

import org.junit.Assert;
import org.junit.Test;

public class OTwoSizeQueueListTest {
  @Test
  public void testSinglePagePushPull() {
    OTwoSizeQueueList queueList = new OTwoSizeQueueList();
    OByteBufferHolder holder = new OByteBufferHolder(null, -1, -1);

    Assert.assertTrue(holder.isAcquired());
    queueList.push(holder);
    Assert.assertFalse(holder.isAcquired());

    Assert.assertSame(holder, queueList.pull());

    Assert.assertNull(queueList.pull());
    Assert.assertNull(queueList.pull());

    Assert.assertTrue(holder.isAcquired());
    queueList.push(holder);
    Assert.assertFalse(holder.isAcquired());

    Assert.assertSame(holder, queueList.pull());
    Assert.assertNull(queueList.pull());
  }

  @Test
  public void testPushPullFour() {
    final OTwoSizeQueueList queueList = new OTwoSizeQueueList();

    final OByteBufferHolder[] holders = new OByteBufferHolder[4];

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }

    Assert.assertNull(queueList.pull());

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }
  }

  @Test
  public void testPushFourRemoveTwoFromTheEnds() {
    final OTwoSizeQueueList queueList = new OTwoSizeQueueList();

    final OByteBufferHolder[] holders = new OByteBufferHolder[4];

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    queueList.remove(holders[0]);
    queueList.remove(holders[3]);

    for (int i = 1; i < 3; i++) {
      Assert.assertSame(holders[i], queueList.pull());
    }

    Assert.assertNull(queueList.pull());

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }
  }

  @Test
  public void testPushFourRemoveTwoFromTheMiddle() {
    final OTwoSizeQueueList queueList = new OTwoSizeQueueList();

    final OByteBufferHolder[] holders = new OByteBufferHolder[4];

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    queueList.remove(holders[1]);
    queueList.remove(holders[2]);

    Assert.assertSame(holders[0], queueList.pull());
    Assert.assertSame(holders[3], queueList.pull());

    Assert.assertNull(queueList.pull());

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }
  }

  @Test
  public void testPushFourRemoveTwoFromTheMiddleAndTop() {
    final OTwoSizeQueueList queueList = new OTwoSizeQueueList();

    final OByteBufferHolder[] holders = new OByteBufferHolder[4];

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    queueList.remove(holders[0]);
    queueList.remove(holders[2]);

    Assert.assertSame(holders[1], queueList.pull());
    Assert.assertSame(holders[3], queueList.pull());

    Assert.assertNull(queueList.pull());

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }
  }

  @Test
  public void testPushFourRemoveTwoFromTheMiddleAndEnd() {
    final OTwoSizeQueueList queueList = new OTwoSizeQueueList();

    final OByteBufferHolder[] holders = new OByteBufferHolder[4];

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    queueList.remove(holders[3]);
    queueList.remove(holders[2]);

    Assert.assertSame(holders[0], queueList.pull());
    Assert.assertSame(holders[1], queueList.pull());

    Assert.assertNull(queueList.pull());

    for (int i = 0; i < holders.length; i++) {
      holders[i] = new OByteBufferHolder(null, -1, -1);
      queueList.push(holders[i]);
    }

    for (OByteBufferHolder holder : holders) {
      Assert.assertSame(holder, queueList.pull());
    }
  }
}