/*
 * Copyright (c) 2015 PocketHub
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
package com.github.pockethub.android.tests.gist;

import android.app.Application;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;

import com.github.pockethub.android.R.id;
import com.github.pockethub.android.core.gist.GistStore;
import com.github.pockethub.android.tests.ActivityTest;
import com.github.pockethub.android.ui.gist.GistFilesViewActivity;
import com.meisolsson.githubsdk.model.Gist;
import com.meisolsson.githubsdk.model.GistFile;
import javax.inject.Inject;

import java.util.Map;

/**
 * Tests of {@link GistFilesViewActivity}
 */
public class GistFilesViewActivityTest extends ActivityTest<GistFilesViewActivity> {

    @Inject
    protected GistStore store;

    private Gist gist;

    /**
     * Create navigation_drawer_header_background
     */
    public GistFilesViewActivityTest() {
        super(GistFilesViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // TODO: Fix this test

        // RoboGuice.injectMembers(getInstrumentation().getTargetContext()
        //     .getApplicationContext(), this);

        Map<String, GistFile> files = new ArrayMap<>();

        GistFile a = GistFile.builder()
                .content("aa")
                .filename("a")
                .build();
        GistFile b = GistFile.builder()
                .content("bb")
                .filename("b")
                .build();

        files.put("a", a);
        files.put("b", b);

        gist = Gist.builder()
                .id("abcd")
                .files(files)
                .build();

        store.addGist(gist);
        setActivityIntent(GistFilesViewActivity.createIntent(gist, 0));
    }

    /**
     * Verify changing pages between gist files
     *
     * @throws Throwable
     */
    public void testChangingPages() throws Throwable {
        final ViewPager pager = (ViewPager) getActivity().findViewById(
            id.vp_pages);
        assertEquals(0, pager.getCurrentItem());
        ui(() -> pager.setCurrentItem(1, true));
        assertEquals(1, pager.getCurrentItem());
        ui(() -> pager.setCurrentItem(0, true));
        assertEquals(0, pager.getCurrentItem());
    }
}
