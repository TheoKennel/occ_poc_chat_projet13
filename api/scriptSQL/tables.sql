CREATE TABLE Users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       phone VARCHAR(15),
                       address VARCHAR(255)
);

CREATE TABLE Agencies (
                          id UUID PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          location VARCHAR(255),
                          phone VARCHAR(15)
);

CREATE TABLE Vehicles (
                          id UUID PRIMARY KEY,
                          make VARCHAR(50) NOT NULL,
                          model VARCHAR(50) NOT NULL,
                          year INT NOT NULL,
                          available BOOLEAN NOT NULL DEFAULT TRUE,
                          agency_id UUID,
                          FOREIGN KEY (agency_id) REFERENCES Agencies(id)
);

CREATE TABLE Bookings (
                          id UUID PRIMARY KEY,
                          user_id UUID,
                          vehicle_id UUID,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          status VARCHAR(50),
                          payment_id UUID,
                          FOREIGN KEY (user_id) REFERENCES Users(id),
                          FOREIGN KEY (vehicle_id) REFERENCES Vehicles(id),
                          FOREIGN KEY (payment_id) REFERENCES Payments(id)
);

CREATE TABLE Payments (
                          id UUID PRIMARY KEY,
                          booking_id UUID,
                          amount DECIMAL(10, 2) NOT NULL,
                          date DATE NOT NULL,
                          status VARCHAR(50),
                          FOREIGN KEY (booking_id) REFERENCES Bookings(id)
);

CREATE TABLE SupportContacts (
                                 id UUID PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 email VARCHAR(100) NOT NULL,
                                 phone VARCHAR(15),
                                 role VARCHAR(50)
);

CREATE TABLE Conversations (
                               id UUID PRIMARY KEY,
                               user_id UUID,
                               contact_id UUID,
                               start_date DATE NOT NULL,
                               status VARCHAR(50),
                               FOREIGN KEY (user_id) REFERENCES Users(id),
                               FOREIGN KEY (contact_id) REFERENCES SupportContacts(id)
);

CREATE TABLE Messages (
                          id UUID PRIMARY KEY,
                          conversation_id UUID,
                          sender VARCHAR(100) NOT NULL,
                          message TEXT NOT NULL,
                          timestamp TIMESTAMP NOT NULL,
                          FOREIGN KEY (conversation_id) REFERENCES Conversations(id)
);

CREATE TABLE Tokens (
                        id UUID PRIMARY KEY,
                        user_id UUID,
                        token TEXT NOT NULL,
                        refresh_token TEXT NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        expires_at TIMESTAMP NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES Users(id)
);
