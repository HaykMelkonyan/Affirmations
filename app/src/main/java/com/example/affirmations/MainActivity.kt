/*
 * Copyright (C) 2021 The Android Open Source Project
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
package com.example.affirmations

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.affirmations.model.Affirmation

class MainActivity : ComponentActivity() {
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataLoaderViewModel.loadData()
        dataLoaderViewModel.affirmationList.observe(this) { affirmationList ->
            setContent {
                AffirmationList(affirmationList = affirmationList)
            }
        }
    }
}


@Composable
fun AffirmationList(affirmationList: List<Affirmation>) {
    LazyColumn {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation)
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation) {
    val context = LocalContext.current
    Column {
        Button(onClick = {
            Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show()
        }, Modifier.height(Dp(100f))) {

        }
        Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceId),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(194.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Text(
            text = LocalContext.current.getString(affirmation.stringResourceId),
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                },
            style = MaterialTheme.typography.h6
        )
    }
    Row(modifier = Modifier.horizontalScroll(ScrollState(1))) {
        Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceId),
            modifier = Modifier
                .height(194.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = LocalContext.current.getString(affirmation.stringResourceId),
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                },
            style = MaterialTheme.typography.h6
        )
        Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(affirmation.stringResourceId),
            modifier = Modifier
                .height(194.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                },
            contentScale = ContentScale.Crop
        )
        Text(
            text = LocalContext.current.getString(affirmation.stringResourceId),
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Toast
                        .makeText(context, "${affirmation.stringResourceId}", Toast.LENGTH_SHORT)
                        .show()
                },
            style = MaterialTheme.typography.h6
        )
    }


}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}
