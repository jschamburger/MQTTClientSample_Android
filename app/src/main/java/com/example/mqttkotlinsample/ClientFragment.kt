package com.example.mqttkotlinsample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import org.eclipse.paho.client.mqttv3.*

class ClientFragment : Fragment() {
    private lateinit var mqttClient : MQTTClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mqttClient.isConnected()) {
                    // Disconnect from MQTT Broker
                    mqttClient.disconnect(object : IMqttActionListener {
                        override fun onSuccess(asyncActionToken: IMqttToken?) {
                            Log.d(this.javaClass.name, "Disconnected")

                            Toast.makeText(context, "MQTT Disconnection success", Toast.LENGTH_SHORT).show()

                            // Disconnection success, come back to Connect Fragment
                            findNavController().navigate(R.id.action_ClientFragment_to_ConnectFragment)
                        }

                        override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                            Log.d(this.javaClass.name, "Failed to disconnect")
                        }
                    })
                } else {
                    Log.d(this.javaClass.name, "Impossible to disconnect, no server connected")
                }
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments passed by ConnectFragment
        val serverURI   = arguments?.getString(MQTT_SERVER_URI_KEY)
        val clientId    = arguments?.getString(MQTT_CLIENT_ID_KEY)
        val username    = arguments?.getString(MQTT_USERNAME_KEY)
        val pwd         = arguments?.getString(MQTT_PWD_KEY)

        // TODO: Open MQTT Broker communication

        // TODO: Connect and login to MQTT Broker

        // Disconnect button
        view.findViewById<Button>(R.id.button_disconnect).setOnClickListener {
            if (mqttClient.isConnected()) {
                // Disconnect from MQTT Broker
                mqttClient.disconnect(object : IMqttActionListener {
                                            override fun onSuccess(asyncActionToken: IMqttToken?) {
                                                Log.d(this.javaClass.name, "Disconnected")

                                                Toast.makeText(context, "MQTT Disconnection success", Toast.LENGTH_SHORT).show()

                                                // Disconnection success, come back to Connect Fragment
                                                findNavController().navigate(R.id.action_ClientFragment_to_ConnectFragment)
                                            }

                                            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                                                Log.d(this.javaClass.name, "Failed to disconnect")
                                            }
                                        })
            } else {
                Log.d(this.javaClass.name, "Impossible to disconnect, no server connected")
            }
        }

        // TODO: publish message

        // TODO: subscribe to topic

        // TODO (optional): unsubscribe from topic
    }
}