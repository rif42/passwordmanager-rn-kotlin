import React from "react";
import { Text, View, StyleSheet, TextInput, Button } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

export default function AddScreen() {
    const [url, onChangeUrl] = React.useState("");
    const [username, onChangeUsername] = React.useState("");
    const [password, onChangePassword] = React.useState("");

    return (
        <SafeAreaView style={styles.container}>
            <Text style={styles.addScreenTitle}>Add New Password</Text>
            <Text style={styles.inputLabel}>URL</Text>
            <TextInput style={styles.input} onChangeText={onChangeUrl} value={url} />
            <Text style={styles.inputLabel}>Username</Text>
            <TextInput style={styles.input} onChangeText={onChangeUsername} value={username} />
            <Text style={styles.inputLabel}>Password</Text>
            <TextInput style={styles.input} onChangeText={onChangePassword} value={password} />
            <View style={styles.inputLabel}>
                <Button
                    title='Create'
                    onPress={() => {
                        console.log("send data to backend");
                    }}></Button>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#25292e",
        justifyContent: "center",
        alignItems: "center",
    },
    addScreenTitle: {
        color: "#fff",
        fontSize: 24,
        fontWeight: 400,
    },
    inputLabel: {
        color: "#fff",
        fontSize: 18,
        paddingTop: 12,
    },
    input: {
        color: "black",
        backgroundColor: "#fff",
        width: 180,
        borderRadius: 10,
        padding: 10,
    },
});
