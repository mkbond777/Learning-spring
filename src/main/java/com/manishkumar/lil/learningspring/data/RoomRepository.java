package com.manishkumar.lil.learningspring.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DT2(m.kumar) on 09/03/22.
 */

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
