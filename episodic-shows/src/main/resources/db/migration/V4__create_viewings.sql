CREATE TABLE viewings (
  id bigint not null auto_increment primary key,
  show_id bigint,
  user_id bigint,
  episode_id bigint,
  timecode int,
  updated_at DATETIME,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (show_id) REFERENCES shows(id),
  FOREIGN KEY (episode_id) REFERENCES episodes(id)
);