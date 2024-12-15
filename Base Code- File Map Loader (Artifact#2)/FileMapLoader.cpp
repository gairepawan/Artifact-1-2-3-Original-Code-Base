#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
#include <sstream>

void loadDataFromFile(const std::string& filename, std::unordered_map<std::string, std::string>& dataMap) {
    std::ifstream file(filename);
    if (!file.is_open()) {
        std::cerr << "Error: Unable to open file " << filename << std::endl;
        return;
    }

    std::string line;
    while (std::getline(file, line)) {
        std::istringstream lineStream(line);
        std::string key, value;

        // Assuming the data is in the format: key,value
        if (std::getline(lineStream, key, ',') && std::getline(lineStream, value)) {
            dataMap[key] = value;
        }
    }

    file.close();
}

void displayData(const std::unordered_map<std::string, std::string>& dataMap) {
    if (dataMap.empty()) {
        std::cout << "No data to display." << std::endl;
        return;
    }

    std::cout << "Loaded Data:" << std::endl;
    for (const auto& entry : dataMap) {
        std::cout << "Key: " << entry.first << ", Value: " << entry.second << std::endl;
    }
}

int main() {
    std::unordered_map<std::string, std::string> dataMap;

    // Path to your file
    const std::string filename = "data.txt";

    // Load data into the unordered map
    loadDataFromFile(filename, dataMap);

    // Display the loaded data
    displayData(dataMap);

    return 0;
}
