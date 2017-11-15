package com.p48.feedbot.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PodcastRepository extends MongoRepository<Podcast, String> {


}
