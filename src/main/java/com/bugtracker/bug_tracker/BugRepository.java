package com.bugtracker.bug_tracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<bug, Long> {

}