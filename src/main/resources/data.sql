
INSERT INTO users (username, password, firstname, enabled) VALUES
('rick@novi.nl', '$2a$12$TCxOXL/GcdpIvCkvsv2tPuyGmSQjUpmZudl9wWCbSVPlDezKft2iG', 'Rick', true),
('fred@novi.nl', '$2a$12$vpDT/ULJLbqYJvTazKL.We5aU20rcA2xCYwQvSUfcocEUyuT3Opji', 'Fred', true),
('marie@novi.nl', '$2a$12$AY17aw31.vSj7nxTBZebQOjfyNnstuROabKeFjkd0bpa7XOYWCFJi', 'Marie', false);

INSERT INTO authorities (username, authority) VALUES
('rick@novi.nl', 'ADMIN'), ('fred@novi.nl', 'USER'), ('marie@novi.nl', 'USER');
