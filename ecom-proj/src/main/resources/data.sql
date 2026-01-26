-- Clear existing data (optional - remove if you want to keep existing data)
DELETE FROM product;

-- Insert sample product data
-- Column order: id, available, brand, category, desc, name, price, quantity, release_date
INSERT INTO product VALUES
(DEFAULT, TRUE, 'Tata Motors', 'Cars', 'A compact SUV with excellent safety features and performance.', 'Tata Nexon', 700000, 10, CURRENT_DATE),
(DEFAULT, TRUE, 'Maruti Suzuki', 'Cars', 'A popular hatchback known for its fuel efficiency and reliability.', 'Maruti Suzuki Swift', 550000, 10, CURRENT_DATE),
(DEFAULT, TRUE, 'Hyundai', 'Cars', 'A stylish SUV with advanced features and comfortable interior.', 'Hyundai Creta', 900000, 10, CURRENT_DATE),
(DEFAULT, TRUE, 'Mahindra', 'Cars', 'A rugged off-road SUV with a powerful engine and modern amenities.', 'Mahindra Thar', 1200000, 10, CURRENT_DATE),
(DEFAULT, TRUE, 'Honda', 'Cars', 'A premium sedan with a sleek design and advanced safety features.', 'Honda City', 1100000, 10, CURRENT_DATE);
