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

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.affirmations.model.Affirmation

class MainActivity : ComponentActivity() {
    private val dataLoaderViewModel: DataLoaderViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataLoaderViewModel.loadAffirmation()
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
    Column {
        AsyncImage(
            model = affirmation.imageUrl,
            contentDescription = affirmation.text,
        placeholder = painterResource(id = R.drawable.image1))
        Text(text = affirmation.text)
    }
}

//@Composable
//fun AffirmationCard(affirmationResources: Affirmation) {
//    val context = LocalContext.current
//    Column {
//        Button(onClick = {
//            Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show()
//        }, Modifier.height(Dp(100f))) {
//
//        }
//        Image(
//            painter = painterResource(affirmationResources.imageResourceId),
//            contentDescription = affirmationResources.text,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(194.dp)
//                .clickable {
//                    showToast(context, affirmationResources)
//                }
//        )
//        Text(
//            text = affirmationResources.text,
//            modifier = Modifier
//                .padding(16.dp)
//                .clickable {
//                    showToast(context, affirmationResources)
//                },
//            style = MaterialTheme.typography.h6
//        )
//    }
//    Row(modifier = Modifier.horizontalScroll(ScrollState(1))) {
//        Image(
//            painter = painterResource(affirmationResources.imageResourceId),
//            contentDescription = affirmationResources.text,
//            modifier = Modifier
//                .height(194.dp)
//                .clickable {
//                    showToast(context, affirmationResources)
//                },
//            contentScale = ContentScale.Crop
//        )
//        Text(
//            text = affirmationResources.text,
//            modifier = Modifier
//                .padding(16.dp)
//                .clickable {
//                    showToast(context, affirmationResources)
//                },
//            style = MaterialTheme.typography.h6
//        )
//    }
//
//
//}


private fun showToast(
    context: Context,
    affirmation: Affirmation
) {
    Toast
        .makeText(context, "${affirmation.text}", Toast.LENGTH_SHORT)
        .show()
}

//@Preview
//@Composable
//private fun AffirmationCardPreview() {
//    AffirmationCard(Affirmation("R.string.affirmation1"))
//}
