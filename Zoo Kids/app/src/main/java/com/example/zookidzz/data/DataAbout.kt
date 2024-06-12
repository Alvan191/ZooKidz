package com.example.zookidzz.data

import com.example.zookidzz.R

object DataAbout {
    val  dataPengenalan = listOf(
        aboutZooKidz(
            id = 1,
            text = "ZooKidz adalah aplikasi seru yang didedikasikan untuk membuka wawasan anak-anak terhadap keanekaragaman hewan di seluruh dunia. Dengan ZooKidz, anak-anak dapat mengeksplorasi dunia hewan tanpa harus khawatir bertemu dengan hewan-hewan tersebut secara langsung. Kami memahami betapa pentingnya pendidikan awal bagi anak-anak, dan aplikasi ini dirancang untuk memberikan pengalaman belajar yang aman, menyenangkan, dan mendalam.",
            logo = R.drawable.logo
        )
    )

    val dataRowZooFirst = listOf(
        rowZoo(
            id = 1,
            text = "Pendidikan yang Menyenangkan",
            description = "ZooKidz memberikan berbagai informasi menarik dan edukatif tentang berbagai jenis hewan, anak-anak akan terlibat dalam petualangan seru yang membuka wawasan mereka tentang alam",
            background = R.drawable.bg_four
        ),
        rowZoo(
            id = 2,
            text = "Keamanan Terjamin",
            description = "ZooKidz memberikan orang tua ketenangan hati karena anak-anak dapat menjelajahi dunia hewan dari kenyamanan rumah mereka. Tidak ada bahaya, hanya pengetahuan yang menambah kecintaan mereka terhadap alam",
            background = R.drawable.bg_one
        ),
    )

    val dataRowZooSecond = listOf(
        rowZoosec(
            id = 1,
            text = "Mendorong Kecintaan pada Alam",
            description = "Melalui ZooKidz, kami mengajak anak-anak untuk menghargai hewan-hewan di sekitar mereka dan memupuk rasa cinta terhadap alam",
            background = R.drawable.bg_two
        ),
        rowZoosec(
            id = 2,
            text = "Informasi Mendalam",
            description = "Tidak hanya memberikan informasi tentang hewan-hewan, tetapi juga melengkapi wawasan dengan gambar-gambar indah dan menarik, fakta-fakta unik yang edukatif",
            background = R.drawable.bg_three
        )
    )

    val dataTeam = listOf(
        teamZoo(
            id = 1,
            nama = "Alvannis Damai Amazihono",
            description = "",
            photo = R.drawable.alvan
        ),
        teamZoo(
            id = 2,
            nama = "Siti Hernawati",
            description = "",
            photo = R.drawable.herna
        ),
        teamZoo(
            id = 3,
            nama = "Risasti Dwi Ardini",
            description = "",
            photo = R.drawable.risasti
        ),
        teamZoo(
            id = 4,
            nama = "Nur Azizah",
            description = "",
            photo = R.drawable.azizah
        ),
        teamZoo(
            id = 5,
            nama = "Nurcahyo",
            description = "",
            photo = R.drawable.cahyo
        )
    )
}

data class aboutZooKidz(
    val id: Int,
    val text: String,
    val logo: Int,
)
data class rowZoo(
    val id: Int,
    val text: String,
    val description: String,
    val background: Int,
)
data class rowZoosec(
    val id: Int,
    val text: String,
    val description: String,
    val background: Int,
)

data class teamZoo(
    val id: Int,
    val nama: String,
    val description: String,
    val photo: Int,
)