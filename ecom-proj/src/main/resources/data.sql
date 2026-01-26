-- Clear existing data (optional - remove if you want to keep existing data)
DELETE FROM product;

-- Insert sample product data
-- Column order: id, brand, category, desc, image_date, image_name, image_type, name, price, product_available, release_date, stock_quantity
INSERT INTO product VALUES
(DEFAULT, 'Tata Motors', 'Cars', 'A compact SUV with excellent safety features and performance.', NULL, NULL, NULL, 'Tata Nexon', 700000, TRUE, CURRENT_DATE, 10),
(DEFAULT, 'Maruti Suzuki', 'Cars', 'A popular hatchback known for its fuel efficiency and reliability.', NULL, NULL, NULL, 'Maruti Suzuki Swift', 550000, TRUE, CURRENT_DATE, 10),
(DEFAULT, 'Hyundai', 'Cars', 'A stylish SUV with advanced features and comfortable interior.', NULL, NULL, NULL, 'Hyundai Creta', 900000, TRUE, CURRENT_DATE, 10),
(DEFAULT, 'Mahindra', 'Cars', 'A rugged off-road SUV with a powerful engine and modern amenities.', NULL, NULL, NULL, 'Mahindra Thar', 1200000, TRUE, CURRENT_DATE, 10),
(DEFAULT, 'Honda', 'Cars', 'A premium sedan with a sleek design and advanced safety features.', NULL, NULL, NULL, 'Honda City', 1100000, TRUE, CURRENT_DATE, 10);
