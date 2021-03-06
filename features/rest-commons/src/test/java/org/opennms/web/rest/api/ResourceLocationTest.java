/*******************************************************************************
 * This file is part of OpenNMS(R).
 * <p>
 * Copyright (C) 2015 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2015 The OpenNMS Group, Inc.
 * <p>
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 * <p>
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * <p>
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 * http://www.gnu.org/licenses/
 * <p>
 * For more information contact:
 * OpenNMS(R) Licensing <license@opennms.org>
 * http://www.opennms.org/
 * http://www.opennms.com/
 *******************************************************************************/

package org.opennms.web.rest.api;

import org.junit.Assert;
import org.junit.Test;

public class ResourceLocationTest {

    @Test
    public void testToString() {
        ResourceLocation location = new ResourceLocation(ApiVersion.Version1, "business-services", "1");
        Assert.assertEquals("/rest/business-services/1", location.toString());

        ResourceLocation location2 = new ResourceLocation(ApiVersion.Version2, "some/path/with/slashes", "and-even-more");
        Assert.assertEquals("/api/v2/some/path/with/slashes/and-even-more", location2.toString());

        ResourceLocation location3 = new ResourceLocation(ApiVersion.Version2, "some/path/with/slash/");
        Assert.assertEquals("/api/v2/some/path/with/slash", location3.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        ResourceLocation location1 = new ResourceLocation(ApiVersion.Version2, "business-services", "1");
        ResourceLocation location2 = new ResourceLocation(ApiVersion.Version2, "business-services", "1");
        Assert.assertEquals(location1, location1);
        Assert.assertEquals(location1.hashCode(), location2.hashCode());
        Assert.assertEquals(location1, location2);

        ResourceLocation location3 = new ResourceLocation(ApiVersion.Version1, "business-services", "1");
        Assert.assertFalse("Is equals, but should not be equal", location1.equals(location3));
        Assert.assertFalse("HashCode is equal, but should not be equal", location1.hashCode() == location3.hashCode());

        ResourceLocation location4 = new ResourceLocation(ApiVersion.Version2, "business-services");
        Assert.assertFalse("Is equals, but should not be equal", location1.equals(location4));
        Assert.assertFalse("HashCode is equal, but should not be equal", location1.hashCode() == location4.hashCode());
    }
}
