package com.example.myanimelist.service.anime

import com.example.myanimelist.dto.AnimeDTO
import com.example.myanimelist.service.IStorage

interface IAnimeStorage : IStorage<List<AnimeDTO>>