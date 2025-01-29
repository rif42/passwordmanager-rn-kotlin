import { Image, StyleSheet, View, Text, Button } from "react-native";

import { HelloWave } from "@/components/HelloWave";
import ParallaxScrollView from "@/components/ParallaxScrollView";
import { ThemedText } from "@/components/ThemedText";
import { ThemedView } from "@/components/ThemedView";
import { Link } from "expo-router";
import { SafeAreaView } from "react-native-safe-area-context";

export default function HomeScreen() {
    return (
        <SafeAreaView style={styles.container}>
            <View style={styles.titleContainer}>
                <Text style={styles.titleContainerText}>Password Manager App</Text>
            </View>
            <View style={styles.stepContainer}></View>
            <View style={styles.stepContainer}>
                <Link style={styles.addButton} href='/(tabs)/add'>
                    Add new password
                </Link>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#25292e",
        alignItems: "center",
    },
    titleContainer: {
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "white",
        width: "100%",
        height: 100,
    },
    titleContainerText: {
        fontSize: 26,
        fontWeight: 600,
        color: "black",
    },
    stepContainer: {
        gap: 8,
        marginBottom: 8,
    },
    reactLogo: {
        height: 178,
        width: 290,
        bottom: 0,
        left: 0,
        position: "absolute",
    },
    addButton: {
        display: "flex",
        padding: 10,
        backgroundColor: "blue",
        color: "white",
        borderRadius: 5,
        textAlign: "center",
    },
});
