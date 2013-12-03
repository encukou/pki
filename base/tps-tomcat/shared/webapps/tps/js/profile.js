/* --- BEGIN COPYRIGHT BLOCK ---
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Copyright (C) 2013 Red Hat, Inc.
 * All rights reserved.
 * --- END COPYRIGHT BLOCK ---
 *
 * @author Endi S. Dewata
 */

var ProfileModel = Backbone.Model.extend({
    urlRoot: "/tps/rest/profiles"
});

var ProfileCollection = Backbone.Collection.extend({
    url: function() {
        return "/tps/rest/profiles";
    },
    parse: function(response) {
        var models = [];

        var list = response.Profiles.Profile;
        list = list == undefined ? [] : [].concat(list);

        _(list).each(function(item) {
            var model = new ProfileModel({
                id: item["@id"],
                status: item.Status
            });
            models.push(model);
        });

        return models;
    }
});