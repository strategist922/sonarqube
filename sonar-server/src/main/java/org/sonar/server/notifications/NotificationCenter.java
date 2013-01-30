/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.server.notifications;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.ServerComponent;
import org.sonar.api.notifications.NotificationChannel;
import org.sonar.api.notifications.NotificationDispatcherMetadata;

import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @since 3.5
 */
public class NotificationCenter implements ServerComponent {

  private static final Logger LOG = LoggerFactory.getLogger(NotificationCenter.class);

  private final NotificationDispatcherMetadata[] dispatchersMetadata;
  private final NotificationChannel[] channels;

  /**
   * Constructor for {@link NotificationCenter} 
   */
  public NotificationCenter(NotificationDispatcherMetadata[] metadata, NotificationChannel[] channels) {
    this.dispatchersMetadata = metadata;
    this.channels = channels;
  }

  /**
   * Default constructor when no channels.
   */
  public NotificationCenter(NotificationDispatcherMetadata[] metadata) {
    this(metadata, new NotificationChannel[0]);
    LOG.warn("There is no notification channel - no notification will be delivered!");
  }

  /**
   * Default constructor when no dispatcher metadata.
   */
  public NotificationCenter(NotificationChannel[] channels) {
    this(new NotificationDispatcherMetadata[0], channels);
  }

  /**
   * Default constructor.
   */
  public NotificationCenter() {
    this(new NotificationDispatcherMetadata[0], new NotificationChannel[0]);
    LOG.warn("There is no notification channel - no notification will be delivered!");
  }

  /**
   * Returns all the available channels.
   */
  public List<NotificationChannel> getChannels() {
    return Arrays.asList(channels);
  }

  /**
   * Returns all the available dispatchers which metadata matches the given property and its value.
   * <br/>
   * If "propertyValue" is null, the verification is done on the existence of such a property (whatever the value).
   */
  public List<String> getDispatcherKeysForProperty(String propertyKey, @Nullable String propertyValue) {
    List<String> keys = Lists.newArrayList();
    for (NotificationDispatcherMetadata metadata : dispatchersMetadata) {
      String dispatcherKey = metadata.getDispatcherKey();
      String value = metadata.getProperty(propertyKey);
      if (value != null) {
        if (propertyValue == null || value.equals(propertyValue)) {
          keys.add(dispatcherKey);
        }
      }
    }
    return keys;
  }

}
