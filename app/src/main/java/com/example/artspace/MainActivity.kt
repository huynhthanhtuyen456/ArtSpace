package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}


@Composable
fun ArtworkContent(
    @StringRes titleStringRes: Int,
    @StringRes artistStringRes: Int,
    @StringRes yearStringRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(titleStringRes),
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(10.dp)
        )
        Text(
            text = "${stringResource(artistStringRes)} " +
                    "(${stringResource(yearStringRes)})",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun ArtworkImage(
    @DrawableRes image: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .height(300.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(contentDescription),
            modifier = modifier.fillMaxSize(),
        )
    }
}

class Artwork(
    @StringRes val titleStringRes: Int,
    @StringRes val artistStringRes: Int,
    @StringRes val yearStringRes: Int,
    @DrawableRes val image: Int,
    val modifier: Modifier = Modifier
) {
    @Composable
    fun DrawArtworkContent() {
        ArtworkContent(
            titleStringRes = titleStringRes,
            artistStringRes = artistStringRes,
            yearStringRes = yearStringRes,
            modifier = modifier
        )
    }

    @Composable
    fun DrawArtworkImage() {
        ArtworkImage(
            image = image,
            contentDescription = titleStringRes,
            modifier = modifier
        )
    }

    @Composable
    fun DrawArtwork() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            DrawArtworkImage()
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            DrawArtworkContent()
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}

@Composable
fun ArtSpaceLayout() {
    val firstArtwork = Artwork(
        titleStringRes = R.string.authur_john_title,
        artistStringRes = R.string.authur_john_artist,
        yearStringRes = R.string.authur_john_year,
        image = R.drawable.authur_john,
        modifier = Modifier
    )

    val secondArtwork = Artwork(
        titleStringRes = R.string.authur_cosplay_title,
        artistStringRes = R.string.authur_cosplay_artist,
        yearStringRes = R.string.authur_cosplay_year,
        image = R.drawable.authur_cosplay
    )

    val thirdArtwork = Artwork(
        titleStringRes = R.string.authur_gun_title,
        artistStringRes = R.string.authur_gun_artist,
        yearStringRes = R.string.authur_gun_year,
        image = R.drawable.authur_gun
    )

    val fourthArtwork = Artwork(
        titleStringRes = R.string.john_martson_title,
        artistStringRes = R.string.john_martson_artist,
        yearStringRes = R.string.john_martson_year,
        image = R.drawable.john_martson
    )

    var currentArtwork by remember { mutableStateOf(firstArtwork) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        currentArtwork.DrawArtwork()
        Row {
            Button(
                onClick = {
                    when (currentArtwork.titleStringRes) {
                        firstArtwork.titleStringRes -> currentArtwork = fourthArtwork
                        secondArtwork.titleStringRes -> currentArtwork = firstArtwork
                        thirdArtwork.titleStringRes -> currentArtwork = secondArtwork
                        else -> currentArtwork = thirdArtwork
                    }
                },
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            ) {
                Text(text = "Previous", style = MaterialTheme.typography.bodySmall)
            }
            Button(
                onClick = {
                    when (currentArtwork.titleStringRes) {
                        firstArtwork.titleStringRes -> currentArtwork = secondArtwork
                        secondArtwork.titleStringRes -> currentArtwork = thirdArtwork
                        thirdArtwork.titleStringRes -> currentArtwork = fourthArtwork
                        else -> currentArtwork = firstArtwork
                    }
                },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            ) {
                Text(text = "Next", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}