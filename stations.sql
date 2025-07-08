-- Create Table
CREATE TABLE IF NOT EXISTS stations(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    line_color VARCHAR(50) NOT NULL
);

-- Insert Grey line stations
INSERT INTO stations (name, line_color) VALUES
('Dhansa Bus Stand','Grey'),
('Najafgarh','Grey'),
('Nangli','Grey'),

-- Insert Blue line stations
INSERT INTO stations (name, line_color) VALUES
('Dwarka Sector 21', 'Blue'),
('Dwarka Sector 8', 'Blue'),
('Dwarka Sector 9', 'Blue'),
('Dwarka Sector 10', 'Blue'),
('Dwarka Sector 11', 'Blue'),
('Dwarka Sector 12', 'Blue'),
('Dwarka Sector 13', 'Blue'),
('Dwarka Sector 14', 'Blue'),
('Dwarka', 'Blue'),
('Dwarka Mor', 'Blue'),
('Nawada', 'Blue'),
('Uttam Nagar West', 'Blue'),
('Uttam Nagar East', 'Blue'),
('Janakpuri West', 'Blue'),
('Janakpuri East', 'Blue'),
('Tilak Nagar', 'Blue'),
('Subhash Nagar', 'Blue'),
('Tagore Garden', 'Blue'),
('Rajouri Garden', 'Blue'),
('Ramesh Nagar', 'Blue'),
('Moti Nagar', 'Blue'),
('Kirti Nagar', 'Blue'),
('Shadipur', 'Blue'),
('Patel Nagar', 'Blue'),
('Rajendra Place', 'Blue'),
('Karol Bagh', 'Blue'),
('Jhandewalan', 'Blue'),
('R K Ashram Marg', 'Blue'),
('Rajiv Chowk', 'Blue'),
('Barakhamba Road', 'Blue'),
('Mandi House', 'Blue'),
('Pragati Maidan', 'Blue'),
('Indraprastha', 'Blue'),
('Yamuna Bank', 'Blue');

-- Insert Yellow Line stations
INSERT INTO stations (name, line_color) VALUES
('Samaypur Badli', 'Yellow'),
('Rohini Sector 18,19', 'Yellow'),
('Haiderpur Badli Mor', 'Yellow'),
('Jahangirpuri', 'Yellow'),
('Adarsh Nagar', 'Yellow'),
('Azadpur', 'Yellow'),
('Model Town', 'Yellow'),
('GTB Nagar', 'Yellow'),
('Vishwa Vidyalaya', 'Yellow'),
('Civil Lines', 'Yellow'),
('Kashmere Gate', 'Yellow'),
('Chandni Chowk', 'Yellow'),
('Chawri Bazar', 'Yellow'),
('New Delhi', 'Yellow'),
('Rajiv Chowk', 'Yellow'),
('Patel Chowk', 'Yellow'),
('Central Secretariat', 'Yellow'),
('Udyog Bhawan', 'Yellow'),
('Lok Kalyan Marg', 'Yellow'),
('Jor Bagh', 'Yellow'),
('INA', 'Yellow'),
('AIIMS', 'Yellow'),
('Green Park', 'Yellow'),
('Hauz Khas', 'Yellow'),
('Malviya Nagar', 'Yellow'),
('Saket', 'Yellow'),
('Qutub Minar', 'Yellow'),
('Chhatarpur', 'Yellow'),
('Sultanpur', 'Yellow'),
('Ghitorni', 'Yellow'),
('Arjangarh', 'Yellow'),
('Guru Dronacharya', 'Yellow'),
('Sikandarpur', 'Yellow'),
('MG Road', 'Yellow'),
('IFFCO Chowk', 'Yellow'),
('Huda City Centre', 'Yellow');

-- Insert Pink Line stations (few examples)
INSERT INTO stations (name, line_color) VALUES
('Majlis Park', 'Pink'),
('Azadpur', 'Pink'),
('Netaji Subhash Place', 'Pink'),
('Shakurpur', 'Pink'),
('Punjabi Bagh West', 'Pink'),
('ESI Hospital', 'Pink'),
('Rajouri Garden', 'Pink'),
('Maya Puri', 'Pink'),
('Naraina Vihar', 'Pink'),
('Delhi Cantt', 'Pink'),
('Durgabai Deshmukh South Campus', 'Pink');

-- Insert Airport Express Line stations
INSERT INTO stations (name, line_color) VALUES
('New Delhi', 'Airport Express'),
('Shivaji Stadium', 'Airport Express'),
('Dhaula Kuan', 'Airport Express'),
('Delhi Aerocity', 'Airport Express'),
('IGI Airport', 'Airport Express'),
('Dwarka Sector 21', 'Airport Express');

-- Insert Magenta Line stations (few examples)
INSERT INTO stations (name, line_color) VALUES
('Botanical Garden', 'Magenta'),
('Okhla Bird Sanctuary', 'Magenta'),
('Jamia Millia Islamia', 'Magenta'),
('Sukhdev Vihar', 'Magenta'),
('Okhla NSIC', 'Magenta'),
('Kalkaji Mandir', 'Magenta'),
('Nehru Enclave', 'Magenta'),
('Greater Kailash', 'Magenta'),
('Chirag Delhi', 'Magenta'),
('Panchsheel Park', 'Magenta'),
('Hauz Khas', 'Magenta');

-- Insert Red Line stations (few examples)
INSERT INTO stations (name, line_color) VALUES
('Rithala', 'Red'),
('Rohini West', 'Red'),
('Rohini East', 'Red'),
('Pitampura', 'Red'),
('Keshav Puram', 'Red'),
('Netaji Subhash Place', 'Red'),
('Kohat Enclave', 'Red'),
('Rajouri Garden', 'Red'),
('Shastri Nagar', 'Red'),
('Inderlok', 'Red'),
('Kanhaiya Nagar', 'Red'),
('Kashmere Gate', 'Red');

-- Insert Violet Line stations (few examples)
INSERT INTO stations (name, line_color) VALUES
('Kashmere Gate', 'Violet'),
('Lal Quila', 'Violet'),
('Jama Masjid', 'Violet'),
('Delhi Gate', 'Violet'),
('ITO', 'Violet'),
('Janpath', 'Violet'),
('Central Secretariat', 'Violet'),
('Khan Market', 'Violet'),
('Jawaharlal Nehru Stadium', 'Violet'),
('Jangpura', 'Violet'),
('Lajpat Nagar', 'Violet'),
('Moolchand', 'Violet');

-- You can keep adding more stations similarly!