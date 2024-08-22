CREATE TABLE Agencies (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          location VARCHAR(255),
                          phone VARCHAR(15)
);

CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       phone VARCHAR(15),
                       address VARCHAR(255),
                       role VARCHAR(16),
                       CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE Vehicles (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          make VARCHAR(50) NOT NULL,
                          model VARCHAR(50) NOT NULL,
                          year INT NOT NULL,
                          available BOOLEAN NOT NULL DEFAULT TRUE,
                          agency_id BIGINT,
                          FOREIGN KEY (agency_id) REFERENCES Agencies(id)
);

CREATE TABLE Conversations (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               status VARCHAR(50)
);

CREATE TABLE Conversation_Users (
                                    conversation_id BIGINT,
                                    user_id BIGINT,
                                    PRIMARY KEY (conversation_id, user_id),
                                    FOREIGN KEY (conversation_id) REFERENCES Conversations(id) ON DELETE CASCADE,
                                    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE Messages (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          conversation_id BIGINT,
                          sender VARCHAR(100) NOT NULL,
                          message TEXT NOT NULL,
                          send_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          FOREIGN KEY (conversation_id) REFERENCES Conversations(id)
);

CREATE TABLE Payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          amount DECIMAL(10, 2) NOT NULL,
                          date DATE NOT NULL,
                          status VARCHAR(50)
);

CREATE TABLE Bookings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT,
                          vehicle_id BIGINT,
                          startDate DATE NOT NULL,
                          endDate DATE NOT NULL,
                          status VARCHAR(50),
                          payment_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES Users(id),
                          FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id),
                          FOREIGN KEY (payment_id) REFERENCES Payments(id)
);

ALTER TABLE Payments
    ADD COLUMN booking_id BIGINT,
ADD FOREIGN KEY (booking_id) REFERENCES Bookings(id);

CREATE TABLE refreshtoken (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT UNIQUE,
                              token VARCHAR(255) NOT NULL UNIQUE,
                              expirationDate TIMESTAMP NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES Users(id)
);



UPDATE Users
SET role = 'ADMIN'
WHERE username = 'admin';
