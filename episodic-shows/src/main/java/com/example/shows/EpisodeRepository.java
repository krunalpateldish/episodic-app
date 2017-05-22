package com.example.shows;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by trainer2 on 5/18/17.
 */

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
    Episode findByShowId(Long showId);

}
