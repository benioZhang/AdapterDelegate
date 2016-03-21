/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.benio.adapterdelegate.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;

import com.benio.adapterdelegate.sample.adapter.ReptilesAdapter;
import com.benio.adapterdelegate.sample.adapter.ReptilesListAdapter;
import com.benio.adapterdelegate.sample.model.DisplayableItem;
import com.benio.adapterdelegate.sample.model.Gecko;
import com.benio.adapterdelegate.sample.model.Snake;
import com.benio.adapterdelegate.sample.model.UnknownReptile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReptilesActivity extends AppCompatActivity {
    ViewGroup mParentViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reptiels);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mParentViewGroup = (ViewGroup) findViewById(R.id.parentView);

        setupRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reptiles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list) {
            setupListView();
            return true;
        } else if (id == R.id.action_recycler) {
            setupRecyclerView();
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        setTitle("RecyclerView");
        mParentViewGroup.removeAllViews();

        final RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ReptilesAdapter adapter = new ReptilesAdapter(getAnimals());
        recyclerView.setAdapter(adapter);
        mParentViewGroup.addView(recyclerView);
    }

    private void setupListView() {
        setTitle("ListView");
        mParentViewGroup.removeAllViews();

        final ListView listView = new ListView(this);
        ReptilesListAdapter adapter = new ReptilesListAdapter(getAnimals());
        listView.setAdapter(adapter);
        mParentViewGroup.addView(listView);
    }

    private List<DisplayableItem> getAnimals() {
        List<DisplayableItem> animals = new ArrayList<>();

        animals.add(new Snake("Mub Adder", "Adder"));
        animals.add(new Snake("Texas Blind Snake", "Blind snake"));
        animals.add(new Snake("Tree Boa", "Boa"));
        animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
        animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
        animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
        animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));
        animals.add(new UnknownReptile());
        animals.add(new UnknownReptile());
        animals.add(new UnknownReptile());

        animals.add(new Snake("Mub Adder", "Adder"));
        animals.add(new Snake("Texas Blind Snake", "Blind snake"));
        animals.add(new Snake("Tree Boa", "Boa"));
        animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
        animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
        animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
        animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));

        animals.add(new Snake("Mub Adder", "Adder"));
        animals.add(new Snake("Texas Blind Snake", "Blind snake"));
        animals.add(new Snake("Tree Boa", "Boa"));
        animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
        animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
        animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
        animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));

        Collections.shuffle(animals);
        return animals;
    }
}
